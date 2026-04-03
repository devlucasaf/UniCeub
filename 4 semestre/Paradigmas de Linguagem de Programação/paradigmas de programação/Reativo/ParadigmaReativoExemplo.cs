using System;
using System.Reactive.Linq;
using System.Reactive.Subjects;
using System.Collections.Generic;
using System.Threading;

namespace ParadigmaReativoExemplo
{
    class Jogador
    {
        public string Nome 
        { 
            get; 
            set; 
        }

        public int Pontos 
        { 
            get; 
            set; 
        }

        public Jogador(string nome)
        {
            Nome = nome;
            Pontos = 0;
        }

        public void AdicionarPontos(int valor)
        {
            Pontos += valor;
        }

        public override string ToString()
        {
            return Nome + " - " + Pontos;
        }
    }

    class Evento
    {
        public string Tipo 
        { 
            get; 
            set; 
        }

        public int Valor 
        { 
            get; 
            set; 
        }

        public Evento(string tipo, int valor)
        {
            Tipo = tipo;
            Valor = valor;
        }

        public override string ToString()
        {
            return Tipo + " (" + Valor + ")";
        }
    }

    class GeradorEventos
    {
        private Random random = new Random();

        public Evento Gerar()
        {
            int t = random.Next(5);
            if (t == 0) 
            {
                return new Evento("MOEDA", 10);
            }

            if (t == 1) 
            {
                return new Evento("INIMIGO", 20);
            }

            if (t == 2) 
            {
                return new Evento("TESOURO", 50);
            }

            if (t == 3) 
            {
                return new Evento("ARMADILHA", -15);
            }
            return new Evento("BONUS", 30);
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            var jogadores = new List<Jogador>
            {
                new Jogador("Lucas"),
                new Jogador("Ana"),
                new Jogador("Pedro"),
                new Jogador("Marina")
            };

            var gerador = new GeradorEventos();

            var fluxoEventos = Observable.Interval(TimeSpan.FromMilliseconds(200))
                .Select(_ => gerador.Gerar());

            var processamento = fluxoEventos
                .SelectMany(ev =>
                    jogadores.ToObservable()
                        .Select(j =>
                        {
                            j.AdicionarPontos(ev.Valor);
                            return j;
                        })
                );

            var sub1 = processamento
                .Buffer(10)
                .Subscribe(lista =>
                {
                    Console.WriteLine("Atualizacao:");
                    foreach (var j in lista)
                    {
                        Console.WriteLine(j);
                    }
                    Console.WriteLine("-----");
                });

            Thread.Sleep(3000);

            var numeros = Observable.Range(1, 100)
                .Select(n => n * 2)
                .Where(n => n % 3 == 0);

            numeros.Subscribe(n => Console.WriteLine("Numero: " + n));

            Thread.Sleep(1000);

            var nomes = new[] { "A", "B", "C", "D", "E" }
                .ToObservable()
                .SelectMany(n => Observable.Return(n).Delay(TimeSpan.FromMilliseconds(50)));

            nomes.Subscribe(n => Console.WriteLine("Nome: " + n));

            Thread.Sleep(1000);

            var quadrados = Observable.Range(1, 50)
                .SelectMany(n => Observable.Return(n * n).Delay(TimeSpan.FromMilliseconds(5)));

            quadrados.Subscribe(n => Console.WriteLine("Quadrado: " + n));

            Thread.Sleep(1000);

            var tempo = Observable.Interval(TimeSpan.FromMilliseconds(100))
                .Take(20);

            tempo.Subscribe(t => Console.WriteLine("Tempo: " + t));

            Thread.Sleep(2000);

            var soma = Observable.Range(1, 100)
                .Scan((a, b) => a + b);

            soma.Subscribe(s => Console.WriteLine("Soma: " + s));

            Thread.Sleep(1000);

            var paralelo = Observable.Range(1, 100)
                .Select(n => n * 2);

            paralelo.Subscribe(n => Console.WriteLine("Paralelo: " + n));

            Thread.Sleep(1000);

            var controle = Observable.Range(1, 20)
                .SelectMany(n =>
                {
                    if (n % 5 == 0)
                    {
                        return Observable.Throw<int>(new Exception());
                    }
                    return Observable.Return(n);
                })
                .Catch(Observable.Return(-1));

            controle.Subscribe(n => Console.WriteLine("Controle: " + n));

            Thread.Sleep(1000);

            var combinacao = Observable.CombineLatest(
                Observable.Interval(TimeSpan.FromMilliseconds(100)),
                Observable.Interval(TimeSpan.FromMilliseconds(150)),
                (a, b) => a + b
            );

            combinacao.Take(20).Subscribe(n => Console.WriteLine("Combinado: " + n));

            Thread.Sleep(2000);

            var janela = Observable.Range(1, 50)
                .Buffer(10)
                .Select(lista =>
                {
                    int somaLocal = 0;
                    foreach (var i in lista)
                    {
                        somaLocal += i;
                    }
                    return somaLocal;
                });

            janela.Subscribe(n => Console.WriteLine("Janela: " + n));

            Thread.Sleep(1000);

            var retry = Observable.Range(1, 10)
                .SelectMany(n =>
                {
                    if (n == 5)
                    {
                        return Observable.Throw<int>(new Exception());
                    }
                    return Observable.Return(n);
                })
                .Retry(1);

            retry.Subscribe(n => Console.WriteLine("Retry: " + n));

            Thread.Sleep(1000);

            var zip = Observable.Zip(
                Observable.Range(1, 10),
                Observable.Range(10, 10),
                (a, b) => a + b
            );

            zip.Subscribe(n => Console.WriteLine("Zip: " + n));

            Thread.Sleep(1000);

            var takeDrop = Observable.Range(1, 100)
                .Take(20)
                .Skip(5);

            takeDrop.Subscribe(n => Console.WriteLine("TakeDrop: " + n));

            Thread.Sleep(1000);

            var reduce = Observable.Range(1, 10)
                .Aggregate((a, b) => a + b);

            reduce.Subscribe(n => Console.WriteLine("Reduce: " + n));

            Thread.Sleep(1000);

            var delay = Observable.Range(1, 20)
                .SelectMany(n => Observable.Return(n).Delay(TimeSpan.FromMilliseconds(30)));

            delay.Subscribe(n => Console.WriteLine("Delay: " + n));

            Thread.Sleep(1000);

            var distinct = new[] { 1, 2, 2, 3, 3, 4, 5, 5 }
                .ToObservable()
                .Distinct();

            distinct.Subscribe(n => Console.WriteLine("Distinct: " + n));

            Thread.Sleep(1000);

            var concat = Observable.Concat(
                Observable.Range(1, 5),
                Observable.Range(6, 5)
            );

            concat.Subscribe(n => Console.WriteLine("Concat: " + n));

            Thread.Sleep(1000);

            var merge = Observable.Merge(
                Observable.Interval(TimeSpan.FromMilliseconds(50)).Select(x => (int)x),
                Observable.Interval(TimeSpan.FromMilliseconds(70)).Select(x => (int)x)
            );

            merge.Take(20).Subscribe(n => Console.WriteLine("Merge: " + n));

            Thread.Sleep(2000);

            var subject = new Subject<int>();

            subject.Subscribe(x => Console.WriteLine("Sub1: " + x));
            subject.Subscribe(x => Console.WriteLine("Sub2: " + x));

            for (int i = 0; i < 20; i++)
            {
                subject.OnNext(i);
                Thread.Sleep(50);
            }

            subject.OnCompleted();

            Thread.Sleep(1000);

            var cadeia = Observable.Range(1, 30)
                .Where(x => x % 2 == 0)
                .Select(x => x * 10)
                .Buffer(5)
                .SelectMany(lista => lista);

            cadeia.Subscribe(x => Console.WriteLine("Cadeia: " + x));

            Thread.Sleep(1000);

            var fluxoExtra1 = Observable.Interval(TimeSpan.FromMilliseconds(30))
                .Take(30)
                .Select(x => x + 1);

            fluxoExtra1.Subscribe(x => Console.WriteLine("Extra1: " + x));

            Thread.Sleep(1000);

            var fluxoExtra2 = Observable.Range(1, 40)
                .SelectMany(x => Observable.Return(x).Delay(TimeSpan.FromMilliseconds(10)));

            fluxoExtra2.Subscribe(x => Console.WriteLine("Extra2: " + x));

            Thread.Sleep(1000);

            var fluxoExtra3 = Observable.Range(1, 50)
                .Select(x => x * 3)
                .Where(x => x % 4 == 0);

            fluxoExtra3.Subscribe(x => Console.WriteLine("Extra3: " + x));

            Thread.Sleep(1000);

            var fluxoExtra4 = Observable.Range(1, 60)
                .Scan((a, b) => a + b);

            fluxoExtra4.Subscribe(x => Console.WriteLine("Extra4: " + x));

            Thread.Sleep(1000);

            var fluxoExtra5 = Observable.Interval(TimeSpan.FromMilliseconds(20))
                .Take(40);

            fluxoExtra5.Subscribe(x => Console.WriteLine("Extra5: " + x));

            Thread.Sleep(2000);
        }
    }
}