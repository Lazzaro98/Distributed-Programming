//semafor

[(i:1..n) x(i)::proc || sem :: sem]

proc:: [
    sem!wait
    sem!signal
]

sem:: [
    int s = 0;
    *[
        s>0; (i:1..n) x(i) ? wait -> [
            s--;
        ]
        []
        (i:1..n) x(i)?signal->[
            s++;
        ]
    ]
]