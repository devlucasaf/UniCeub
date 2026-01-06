/* UNICEUB
Teoria da Computação
Atividade: UA1
Data: 07-10-2025
*/

[q0] --0.25--> [q25] --0.25--> [q50] --0.25--> [q75] --0.25--> [q100]
|               |              |               |
    0.50            0.50           0.50 (excede)    0.25/0.50 (excede)
    v               v              v               v
[q50] <--0.25-- [q75] <--0.25-- [q100]  (quando excede) --→ [q0]

Selecione em:
    q25 -> acc_b (bombom R$0,25)
    q50 -> acc_c (chiclete R$0,50)
    q75 -> acc_p (pirulito R$0,75)
    q100-> acc_pa (paçoca R$1,00)

Selecione em q0 (ou em qualquer estado com total que não corresponde a preço exato) -> cancelamento -> volta a q0

