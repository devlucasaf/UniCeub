dobro :: [Int] -> [Int]
dobro [] = []
dobro (x:xs) = x*2 : dobro xs

dobro2 a = [x*2 | x <- a]
-- map (*2) [1,2,3,5]

funcao a = [x*x + 2*x + 1 | x <- a]