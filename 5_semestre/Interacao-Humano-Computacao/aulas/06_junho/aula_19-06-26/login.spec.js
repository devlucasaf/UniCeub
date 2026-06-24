const { test, expect } = require('@playwright/test');

test('login válido', async ({ page }) => {
    await page.goto('http://localhost:3000/');
    await page.fill('input[name="username"]', 'aluno');
    await page.fill('input[name="password"]', 'senha123');
    await page.click('button[type="submit"]');
    await expect(page.locator('body')).toContainText('Bem-vindo!', { timeout: 3000 });
});

test('login inválido', async ({ page }) => {
    await page.goto('http://localhost:3000/');
    await page.fill('input[name="username"]', 'x');
    await page.fill('input[name="password"]', 'y');
    await page.click('button[type="submit"]');
    await expect(page.locator('body')).toContainText('Credenciais inválidas', { timeout: 3000 });
});

