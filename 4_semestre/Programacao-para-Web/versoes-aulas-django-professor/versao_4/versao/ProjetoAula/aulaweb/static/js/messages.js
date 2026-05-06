document.addEventListener('DOMContentLoaded', function() {
    initializeMessages();
});

function initializeMessages() {
    const messagesContainer = document.querySelector('.messages');
    
    if (!messagesContainer) {
        return;
    }
    
    const alerts = messagesContainer.querySelectorAll('.alert');
    
    alerts.forEach((alert, index) => {
        if (!alert.id) {
            alert.id = `message-${Date.now()}-${index}`;
        }
        
        const closeBtn = alert.querySelector('.alert-close');
        if (closeBtn) {
            closeBtn.addEventListener('click', function() {
                closeMessage(alert);
            });
            
            closeBtn.addEventListener('keydown', function(e) {
                if (e.key === 'Enter' || e.key === ' ') {
                    e.preventDefault();
                    closeMessage(alert);
                }
            });
        }
        
        setupAutoRemove(alert);
        
        alert.addEventListener('click', function(e) {
            if (e.target.classList.contains('alert-close')) {
                return;
            }
            if (confirm('Deseja fechar esta mensagem?')) {
                closeMessage(alert);
            }
        });
        
        if (index === 0) {
            setTimeout(() => alert.setAttribute('tabindex', '-1'), 100);
        }
    });
    
    addCloseAllButton(messagesContainer, alerts);
    setupKeyboardShortcuts(alerts);
}

function closeMessage(alertElement) {
    alertElement.classList.add('fade-out');
    
    setTimeout(() => {
        if (alertElement.parentNode) {
            alertElement.parentNode.removeChild(alertElement);
            
            const messagesContainer = document.querySelector('.messages');
            if (messagesContainer && messagesContainer.children.length === 0) {
                messagesContainer.style.display = 'none';
            }
            
            const event = new CustomEvent('messageClosed', {
                detail: { messageId: alertElement.id }
            });
            document.dispatchEvent(event);
        }
    }, 300);
}

function setupAutoRemove(alertElement) {
    const alertType = Array.from(alertElement.classList)
        .find(className => className.startsWith('alert-'))
        ?.replace('alert-', '');
    
    const autoRemoveTimes = {
        'success': 5000,
        'info': 7000,
        'warning': 10000,
        'error': 15000,
        'debug': 3000
    };
    
    const removeTime = autoRemoveTimes[alertType] || autoRemoveTimes.info;
    
    if (removeTime) {
        alertElement.classList.add('auto-remove');
        
        const autoRemoveTimeout = setTimeout(() => {
            if (document.body.contains(alertElement)) {
                closeMessage(alertElement);
            }
        }, removeTime);
        
        alertElement.dataset.autoRemoveTimeout = autoRemoveTimeout;
        
        alertElement.addEventListener('mouseenter', function() {
            clearTimeout(autoRemoveTimeout);
            this.classList.remove('auto-remove');
        });
        
        alertElement.addEventListener('focusin', function() {
            clearTimeout(autoRemoveTimeout);
            this.classList.remove('auto-remove');
        });
    }
}

function addCloseAllButton(container, alerts) {
    if (alerts.length <= 1) {
        return;
    }
    
    const closeAllBtn = document.createElement('button');
    closeAllBtn.className = 'close-all-btn';
    closeAllBtn.textContent = 'Fechar todas';
    closeAllBtn.setAttribute('aria-label', 'Fechar todas as mensagens');
    
    closeAllBtn.addEventListener('click', function() {
        if (confirm('Deseja fechar todas as mensagens?')) {
            const allAlerts = container.querySelectorAll('.alert');
            allAlerts.forEach(alert => closeMessage(alert));
        }
    });
    
    closeAllBtn.style.cssText = `
        display: block;
        margin: 10px auto 0;
        padding: 6px 12px;
        background: #6b7280;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 0.9rem;
        transition: background-color 0.2s ease;
    `;
    
    closeAllBtn.addEventListener('mouseenter', function() {
        this.style.backgroundColor = '#4b5563';
    });
    
    closeAllBtn.addEventListener('mouseleave', function() {
        this.style.backgroundColor = '#6b7280';
    });
    
    container.appendChild(closeAllBtn);
}

function setupKeyboardShortcuts(alerts) {
    document.addEventListener('keydown', function(e) {
        if (e.ctrlKey && e.shiftKey && e.key === 'X') {
            e.preventDefault();
            const closeAllBtn = document.querySelector('.close-all-btn');
            if (closeAllBtn) closeAllBtn.click();
        }
        
        if (e.key === 'Escape') {
            const focusedMessage = document.querySelector('.alert:focus-within');
            if (focusedMessage) {
                const closeBtn = focusedMessage.querySelector('.alert-close');
                if (closeBtn) closeBtn.click();
            }
        }
    });
}

function addMessage(text, type = 'info') {
    const messagesContainer = document.querySelector('.messages') || createMessagesContainer();
    
    const alert = document.createElement('div');
    alert.className = `alert alert-${type}`;
    alert.setAttribute('role', 'alert');
    alert.innerHTML = `
        <span>${text}</span>
        <button type="button" class="alert-close" aria-label="Fechar mensagem">&times;</button>
    `;
    
    messagesContainer.appendChild(alert);
    initializeMessages();
    
    alert.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
    
    return alert.id;
}

function createMessagesContainer() {
    const container = document.createElement('div');
    container.className = 'messages';
    container.setAttribute('role', 'alert');
    container.setAttribute('aria-live', 'polite');
    container.setAttribute('aria-atomic', 'true');
    
    const mainElement = document.querySelector('main');
    if (mainElement) {
        mainElement.insertBefore(container, mainElement.firstChild);
    } else {
        document.body.insertBefore(container, document.body.firstChild);
    }
    
    return container;
}

function clearAllMessages() {
    const messagesContainer = document.querySelector('.messages');
    if (messagesContainer) {
        const alerts = messagesContainer.querySelectorAll('.alert');
        alerts.forEach(alert => closeMessage(alert));
    }
}

if (typeof window !== 'undefined') {
    window.MessagesManager = {
        addMessage,
        clearAllMessages,
        initializeMessages
    };
}