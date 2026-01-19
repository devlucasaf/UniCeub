async function getUserProfile() {
    try {
        const token = localStorage.getItem('access_token');
        
        const response = await fetch('/api/profile/', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });
        
        if (response.ok) {
            const userData = await response.json();
            console.log('Dados do usuário:', userData);
            return userData;
        } else {
            console.error('Erro ao buscar dados do usuário');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
}

async function updateUserProfile(updatedData) {
    try {
        const token = localStorage.getItem('access_token');
        
        const response = await fetch('/api/profile/', {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedData)
        });
        
        if (response.ok) {
            const userData = await response.json();
            console.log('Dados atualizados:', userData);
            return userData;
        } else {
            console.error('Erro ao atualizar dados');
        }
    } catch (error) {
        console.error('Erro:', error);
    }
}

getUserProfile().then(user => {
    document.getElementById('nome').value = user.first_name;
    document.getElementById('email').value = user.email;
});
