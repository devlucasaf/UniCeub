-- Demonstração de programação funcional em Haskell
-- Paradigma funcional: funções puras, imutabilidade, composição

-- Definindo uma função pura que soma dois números
soma :: Int -> Int -> Int
soma x y = x + y

-- Função que multiplica dois números
multiplica :: Int -> Int -> Int
multiplica x y = x * y

-- Função que calcula o quadrado de um número
quadrado :: Int -> Int
quadrado x = x * x

-- Função que calcula o cubo de um número
cubo :: Int -> Int
cubo x = x * x * x

-- Função que aplica desconto funcionalmente
aplicaDesconto :: Double -> Double -> Double
aplicaDesconto preco desconto = preco - (preco * desconto)

-- Função que filtra números pares de uma lista
filtraPares :: [Int] -> [Int]
filtraPares xs = filter even xs

-- Função que filtra números ímpares de uma lista
filtraImpares :: [Int] -> [Int]
filtraImpares xs = filter odd xs

-- Função que soma todos os elementos de uma lista
somaLista :: [Int] -> Int
somaLista xs = foldl (+) 0 xs

-- Função que multiplica todos os elementos de uma lista
produtoLista :: [Int] -> Int
produtoLista xs = foldl (*) 1 xs

-- Função que aplica quadrado em todos os elementos de uma lista
quadradosLista :: [Int] -> [Int]
quadradosLista xs = map quadrado xs

-- Função que aplica cubo em todos os elementos de uma lista
cubosLista :: [Int] -> [Int]
cubosLista xs = map cubo xs

-- Função recursiva para calcular fatorial
fatorial :: Int -> Int
fatorial 0 = 1
fatorial n = n * fatorial (n - 1)

-- Função recursiva para calcular Fibonacci
fibonacci :: Int -> Int
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n = fibonacci (n - 1) + fibonacci (n - 2)

-- Função que demonstra composição de funções
dobroMaisUm :: Int -> Int
dobroMaisUm = (+1) . (*2)

-- Função que demonstra uso de funções de ordem superior
aplicaFuncaoLista :: (Int -> Int) -> [Int] -> [Int]
aplicaFuncaoLista f xs = map f xs

-- Função principal (main) para executar exemplos
main :: IO ()
main = do
    putStrLn "=== Demonstração de Programação Funcional em Haskell ==="
    putStrLn $ "Soma 3 + 5 = " ++ show (soma 3 5)
    putStrLn $ "Multiplica 4 * 6 = " ++ show (multiplica 4 6)
    putStrLn $ "Quadrado de 7 = " ++ show (quadrado 7)
    putStrLn $ "Cubo de 3 = " ++ show (cubo 3)
    putStrLn $ "Preço com desconto: " ++ show (aplicaDesconto 100 0.2)
    putStrLn $ "Lista de pares: " ++ show (filtraPares [1..10])
    putStrLn $ "Lista de ímpares: " ++ show (filtraImpares [1..10])
    putStrLn $ "Soma da lista [1..5] = " ++ show (somaLista [1..5])
    putStrLn $ "Produto da lista [1..5] = " ++ show (produtoLista [1..5])
    putStrLn $ "Quadrados da lista [1..5] = " ++ show (quadradosLista [1..5])
    putStrLn $ "Cubos da lista [1..5] = " ++ show (cubosLista [1..5])
    putStrLn $ "Fatorial de 5 = " ++ show (fatorial 5)
    putStrLn $ "Fibonacci de 10 = " ++ show (fibonacci 10)
    putStrLn $ "Dobro mais um de 4 = " ++ show (dobroMaisUm 4)
    putStrLn $ "Aplicando quadrado na lista [1..5] = " ++ show (aplicaFuncaoLista quadrado [1..5])
