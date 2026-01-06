const EventEmitter = require('events');

const emissor = new EventEmitter();

emissor.on('mensagemRecebida', (texto) => {
    console.log("Evento disparado! Mensagem:", texto);
});

setTimeout(() => {
    emissor.emit('mensagemRecebida', "Olá, mundo dos eventos!");
}, 2000);

console.log("Aguardando eventos...");
