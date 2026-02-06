--Criar uma função que calcule o fatorial de n
fatorial 0 = 1
fatorial n = n * fatorial (n-1)

-- Criar uma função que calcule 2^^x usando recursividade
valor :: Int -> Int
valor 0 = 1
valor n = 2 * valor (n-1)

-- Criar uma função que calcule n^^x usando recursividade
potn :: Int -> Int -> Int
potn n 0 = 1
potn n x = n * (potn n (x-1))