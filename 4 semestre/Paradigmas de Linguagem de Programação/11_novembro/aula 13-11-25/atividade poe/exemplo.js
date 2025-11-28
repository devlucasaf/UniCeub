const EventEmitter = require('events');

// Criando um emissor de eventos
const emissor = new EventEmitter();

// Registrando um "ouvinte" (handler) para o evento
emissor.on('mensagemRecebida', (texto) => {
    console.log("Evento disparado! Mensagem:", texto);
});

// Simulando disparo de eventos
setTimeout(() => {
    emissor.emit('mensagemRecebida', "Olá, mundo dos eventos!");
}, 2000);

console.log("Aguardando eventos...");
