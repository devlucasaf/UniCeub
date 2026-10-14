import executarExemploFuncional         from "./examples/exemploFuncional.js";
import executarExemploImperativo        from "./examples/exemploImperativo.js";
import executarExemploMetaprogramacao   from "./examples/exemploMetaprogramacao.js";
import executarExemploIntegracao        from "./examples/exemploIntegracao.js";

console.log("Demonstração dos paradigmas do JavaScript");

console.log("============================================================");

const numeros = executarExemploFuncional();

console.log("============================================================");

executarExemploImperativo(numeros);

console.log("============================================================");

executarExemploMetaprogramacao();

console.log("============================================================");

executarExemploIntegracao();

console.log("============================================================");

console.log("Este código demonstra JavaScript como linguagem multiparadigma!");
