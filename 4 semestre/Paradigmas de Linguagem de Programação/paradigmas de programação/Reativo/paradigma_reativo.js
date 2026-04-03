class Signal {
    constructor(initialValue) {
        this.value = initialValue;
        this.subscribers = new Set();
    }

    get() {
        if (activeEffect) {
            this.subscribers.add(activeEffect);
            activeEffect.dependencies.add(this);
        }
        return this.value
    }

    set(newValue) {
        if (this.value !== newValue) {
            this.value = newValue;
            this.notify();
        }
    }

    notify() {
        for (const effect of this.subscribers) {
            effect.schedule();
        }
    }
}

let activeEffect = null;
const pendingEffects = new Set();
let isBatchPending = false;

function flushEffects() {
    if (!isBatchPending) {
        return;
    }

    const effectsToRun = Array.from(pendingEffects);
    pendingEffects.clear();
    isBatchPending = false;

    for (const effect of effectsToRun) {
        if (effect.shouldRun) {
            effect.run();
        }
    }
}

function scheduleEffect(effect) {
    pendingEffects.add(effect);

    if (!isBatchPending) {
        isBatchPending = true;
        Promise.resolve().then(flushEffects);
    }
}

class Effect {
    constructor(fn) {
        this.fn = fn;
        this.dependencies = new Set();
        this.shouldRun = true;
        this.run();
    }

    run() {
        this.cleanup();
        activeEffect = this;
        this.shouldRun = false;
        this.fn();
        activeEffect = null;
    }

    cleanup() {
        for (const dep of this.dependencies) {
            dep.subscribers.delete(this);
        }
        this.dependencies.clear();
    }

    schedule() {
        this.shouldRun = true;
        scheduleEffect(this);
    }
}

class Computed {
    constructor(computeFn) {
        this.computeFn = computeFn;
        this.value = undefined;
        this.dependencies = new Set();
        this.isDirty = true;
        this.effect = null;
        this.initialize();
    }

    initialize() {
        this.effect = new Effect(() => {
            this.isDirty = true;
            const newValue = this.computeFn();

            if (this.value !== newValue) {
                this.value = newValue;
                this.notify();
            }
            this.isDirty = false;
        });
    }

    get() {
        if (activeEffect) {
            activeEffect.dependencies.add(this);
            this.subscribers = this.subscribers || new Set();
            this.subscribers.add(activeEffect);
        }   

        if (this.isDirty) {
            this.value = this.computeFn();
            this.isDirty = false;
        }
        return this.value;
    }

    notify() {
        if (this.subscribers) {
            for (const effect of this.subscribers) {
                effect.schedule();
            }
        }
    }
}

class ReactiveObject {
    constructor(obj) {
        this.proxies = new WeakMap();
        this.rawToProxy = new WeakMap();
        this.proxyToRaw = new WeakMap();
        return this.createReactive(obj);
    }

    createReactive(obj) {
        if (this.rawToProxy.has(obj)) {
            return this.rawToProxy.get(obj);
        }

        if (this.proxyToRaw.has(obj)) {
            return obj;
        }

        const proxy = new Proxy(obj, {
            get: (target, key, receiver) => {
                const result = Reflect.get(target, key, receiver);

                if (typeof result === 'object' && result !== null) {
                    return this.createReactive(result);
                }

                const signal = this.getSignal(target, key);

                if (activeEffect) {
                    signal.get();
                }
                return signal.get();
            },
            set: (target, key, value, receiver) => {
                const oldValue = Reflect.get(target, key, receiver);

                if (oldValue !== value) {
                    const signal = this.getSignal(target, key);
                    signal.set(value);
                    Reflect.set(target, key, value, receiver);
                }
                return true;
            }
        });

        this.rawToProxy.set(obj, proxy);
        this.proxyToRaw.set(proxy, obj);
        return proxy;
    }

    getSignal(target, key) {
        if (!target.__signals) {
            Object.defineProperty(target, '__signals', {
                value: new Map(),
                enumerable: false,
                configurable: false,
                writable: false
            });
        }
        if (!target.__signals.has(key)) {
            target.__signals.set(key, new Signal(Reflect.get(target, key)));
        }
        return target.__signals.get(key);
    }
}

function reactive(obj) {
    return new ReactiveObject(obj);
}

function ref(initialValue) {
    const signal = new Signal(initialValue);

    return {
        get value() {
            return signal.get();
        },
        set value(newVal) {
            signal.set(newVal);
        }
    }
}

function computed(fn) {
    return new Computed(fn);
}

function watchEffect(fn) {
    return new Effect(fn);
}

function batch(fn) {
    isBatchPending = true;
    fn();
    flushEffects();
}

let counter = ref(0);
let double = computed(() => counter.value * 2);
let quadruple = computed(() => double.get() * 2);

watchEffect(() => {
    console.log(`Counter: ${counter.value}, Double: ${double.get()}, Quadruple: ${quadruple.get()}`);
})

counter.value = 1;
counter.value = 2;
counter.value = 3;

let person = reactive({ name: 'Alice', age: 30 });
let birthYear = computed(() => new Date().getFullYear() - person.age);

watchEffect(() => {
    console.log(`${person.name} is ${person.age} years old, born in ${birthYear.get()}`);
})

person.age = 31;
person.name = 'Bob';

let todos = reactive([
    { 
        text: 'Learn Reactivity', 
        completed: false 
    },
    { 
        text: 'Build a project', 
        completed: false 
    }
]);

let activeTodos = computed(() => todos.filter(t => !t.completed));
let completedTodos = computed(() => todos.filter(t => t.completed));

watchEffect(() => {
    console.log(`Active: ${activeTodos.get().length}, Completed: ${completedTodos.get().length}`);
})

todos[0].completed = true;
todos.push({ 
    text: 'Write documentation', 
    completed: false 
});

let a = ref(10);
let b = ref(20);
let sum = computed(() => a.value + b.value);
let product = computed(() => a.value * b.value);

watchEffect(() => {
    console.log(`Sum: ${sum.get()}, Product: ${product.get()}`);
})

a.value = 15;
b.value = 25;

let deepState = reactive({
    user: {
        profile: {
            name: 'John',
            settings: {
                theme: 'dark'
            }
        }
    }
});

watchEffect(() => {
    console.log(`Theme: ${deepState.user.profile.settings.theme}`);
})

deepState.user.profile.settings.theme = 'light';

function manualCleanup() {
    let stop = watchEffect(() => {
        console.log(`Running effect that will be stopped after 1 second: ${counter.value}`);
    })
    setTimeout(() => {
        stop.cleanup();
        console.log('Effect stopped');
    }, 1000);
}

manualCleanup();

let count = ref(0);
let increment = () => count.value++;

setInterval(() => {
    increment();
}, 2000);

let asyncData = ref(null);
let loading = ref(false);

watchEffect(async () => {
    if (loading.value) {
        return;
    }

    loading.value = true;
    console.log('Fetching data...');
    await new Promise(r => setTimeout(r, 1000));

    asyncData.value = { 
        data: 'Sample data', 
        timestamp: Date.now() 
    }

    loading.value = false;
    console.log('Data fetched:', asyncData.value);
});

let condition = ref(true);
let conditionalValue = computed(() => condition.value ? 'Yes' : 'No');

watchEffect(() => {
    console.log(`Condition is: ${conditionalValue.get()}`);
});

condition.value = false;

let list = reactive([1, 2, 3]);
let listSum = computed(() => list.reduce((acc, val) => acc + val, 0));

watchEffect(() => {
    console.log(`List: ${list.join(', ')}, Sum: ${listSum.get()}`);
});

list.push(4);
list[0] = 10;
list.splice(1, 1);

let objWithNested = reactive({
    nested: {
        deep: {
            value: 42
        }
    }
});

watchEffect(() => {
    console.log(`Deep nested value: ${objWithNested.nested.deep.value}`);
});

objWithNested.nested.deep.value = 100;

let mapExample = reactive(
    new Map(
        [
            [
                'key1', 
                'value1'
            ], 
            [
                'key2',
                'value2'
            ]
        ]
    )
);

watchEffect(() => {
    console.log(`Map size: ${mapExample.size}, key1: ${mapExample.get('key1')}`);
});

mapExample.set('key3', 'value3');
mapExample.set('key1', 'new value');

let setExample = reactive(new Set([1, 2, 3]));

watchEffect(() => {
    console.log(`Set size: ${setExample.size}, has 2: ${setExample.has(2)}`);
});

setExample.add(4);
setExample.delete(1);

let dateSignal = ref(new Date());

watchEffect(() => {
    console.log(`Current date: ${dateSignal.value.toISOString()}`);
});

setTimeout(() => {
    dateSignal.value = new Date();
}, 500);

let complexComputed = computed(() => {
    let c = counter.value;
    let d = double.get();
    let p = person.age;

    return { c, d, p, total: c + d + p }
});

watchEffect(() => {
    let result = complexComputed.get();
    console.log(`Complex computed: ${JSON.stringify(result)}`);
});

counter.value = 5;
person.age = 35;

let willThrowError = computed(() => {
    if (counter.value < 0) {
        throw new Error('Counter cannot be negative');
    }
    return counter.value * 10;
});

watchEffect(() => {
    try {
        console.log(`Safe computed: ${willThrowError.get()}`);
    } 
    
    catch (e) {
        console.log(`Caught error: ${e.message}`);
    }
});

counter.value = -1;

let batchExample1 = ref(0);
let batchExample2 = ref(0);
let batchSum = computed(() => batchExample1.value + batchExample2.value);

watchEffect(() => {
    console.log(`Batch sum: ${batchSum.get()}`);
});

batch(() => {
    batchExample1.value = 10;
    batchExample2.value = 20;
});

let cleanEffect = watchEffect(() => {
    console.log(`Cleanup test: ${counter.value}`);
    return () => console.log('Effect cleaned up');
});

cleanEffect.cleanup();

let manySignals = [];

for (let i = 0; i < 100; i++) {
    manySignals.push(ref(i));
}

let manySum = computed(() => manySignals.reduce((acc, s) => acc + s.value, 0));

watchEffect(() => {
    console.log(`Sum of 100 signals: ${manySum.get()}`);
});

manySignals[50].value = 999;

let cycleA = ref(1);
let cycleB = computed(() => cycleA.value + 1);
let cycleC = computed(() => cycleB.get() + 1);

watchEffect(() => {
    console.log(`Cycle chain: A=${cycleA.value}, B=${cycleB.get()}, C=${cycleC.get()}`);
});

cycleA.value = 10;

let performanceTest = ref(0);
let perfComputed = computed(() => {
    let sum = 0;
    for (let i = 0; i < 1000; i++) {
        sum += performanceTest.value;
    }
    return sum;
});

watchEffect(() => {
    console.log(`Performance computed result: ${perfComputed.get()}`);
});

performanceTest.value = 5;

let immediateEffect = watchEffect(() => {
    console.log(`This effect runs immediately: ${counter.value}`);
})

let stopEffect = watchEffect(() => {
    console.log(`This effect will be stopped after 2 seconds: ${Math.random()}`);
});

setTimeout(() => stopEffect.cleanup(), 2000);

let derivedFromRef = computed(() => {
    let base = counter.value;

    return {
        square: base * base,
        cube: base * base * base
    }
});

watchEffect(() => {
    let { square, cube } = derivedFromRef.get();
    console.log(`Square: ${square}, Cube: ${cube}`);
});

counter.value = 4;

let reactiveArray = reactive([{ val: 1 }, { val: 2 }]);
let arrayComputed = computed(() => reactiveArray.map(item => item.val * 2));

watchEffect(() => {
    console.log(`Array computed: ${arrayComputed.get().join(', ')}`);
});

reactiveArray[0].val = 10
reactiveArray.push({ val: 3 })

let lazyComputed = computed(() => {
    console.log('Lazy computed recomputed');
    return counter.value * 100;
});

console.log(`Lazy value: ${lazyComputed.get()}`);

counter.value = 2;

console.log(`Lazy value again: ${lazyComputed.get()}`);

let nestedReactive = reactive({
    level1: {
        level2: {
            level3: ref(42)
        }
    }
});

watchEffect(() => {
    console.log(`Nested ref inside reactive: ${nestedReactive.level1.level2.level3.value}`);
});

nestedReactive.level1.level2.level3.value = 99;

let signalWithEquality = new Signal(0);

signalWithEquality.set(0);
signalWithEquality.set(0);
signalWithEquality.set(1);

watchEffect(() => {
    console.log(`Signal with equality check: ${signalWithEquality.get()}`);
});

console.log('Reactivity system fully operational');