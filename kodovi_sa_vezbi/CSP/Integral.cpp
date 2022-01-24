//bag of tasks
// primer principa torbe poslova
[Node(p : 1..n) :: NODE || Bag::BAG]

NODE::[ left, right, data : double; // dohvata granice integrala, i vraca rezultat koji izracuna
    *[
        Bag!getTask();
        Bag?getData(left, right) -> CALCULATE;
        Bag!putResult(data);
    ]
]

BAG:: [Xmin, Xmax, dx, x, F:double; F:=0;
    N, i:integer; i:= 0;
    INIT;
    *[
        i<N, x<Xmax. (j:1..n) Node(j)?getTask() -> [
            Node(j)!getData(x, x+dx);
            x := x + dx
        ]
        []
        i<N; (j:1..n) Node(j)? putResult(data) -> [
            F := F + data;
            i := i + 1
        ]
    ]
    STOP;
]