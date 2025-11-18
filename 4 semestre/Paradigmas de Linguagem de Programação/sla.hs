funcao :: Float -> Float
funcao x = 2 * x
y = funcao 2

funcao2 :: Float -> Float -> Float
funcao2 w x = ((2*10**(-3) * w) / (3.14 * 2*10**(-13))) ** (1/4) * 2.71**((-2*10**(-3) * w * x * x) / (2 * 2*10**(-13)))

aluno :: Int -> Float
aluno 1 = 7.5
aluno 2 = 8
aluno 3 = 4
aluno 4 = 10
aluno 5 = 2

imprimeAluno :: Int -> String
imprimeAluno n = show n ++ " - " ++ show (aluno n)

imprimeAlunos :: Int -> String
imprimeAlunos 1 = imprimeAluno 1
imprimeAlunos n = imprimeAlunos (n-1) ++ " | " ++ imprimeAluno n

main :: IO ()
main = do
    putStrLn "Calculando o valor da funcao f (x) = 2x, para x = 2"
    print (funcao 2)
