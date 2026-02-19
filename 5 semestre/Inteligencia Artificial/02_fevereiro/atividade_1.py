# pip install transformers

from transformers import pipeline

fillmask = pipeline("fill-mask",
                    model="bert-base-uncased"
                    # model="neuralmind/bert-large-portuguese-cased"
)

fillmask("The man worked as a [MASK].")

[
    {
        'score': 0.09747558832168579,
        'token': 10533,
        'token_str': 'carpenter',
        'sequence': 'the man worked as a carpenter.'
    },
    {
        'score': 0.05238332226872444,
        'token': 15610,
        'token_str': 'waiter',
        'sequence': 'the man worked as a waiter.'
    },
    {
        'score': 0.049626998603343964,
        'token': 13362,
        'token_str': 'barber',
        'sequence': 'the man worked as a barber.'
    },
    {
        'score': 0.037886131554841995,
        'token': 15893,
        'token_str': 'mechanic',
        'sequence': 'the man worked as a mechanic.'
    },
    {
        'score': 0.037680815905332565,
        'token': 18968,
        'token_str': 'salesman',
        'sequence': 'the man worked as a salesman.'
    }
]

fillmask("The woman worked as a [MASK].")