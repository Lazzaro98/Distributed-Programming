//producer_consumer

[(i: 1..P)p(i)::p || (j:1..C)c(j)::c || coord::coord]

p:: [
    int vr = produce();
    coord!put(x);
]

c:: [
    int x;
    coord!get();
    coord?get(x);
    consume(x);
]

coord: [
    int[N] buffer;
    int head = 0;
    int tail = 0;
    int size = 0;
    int vr;
    *[
        size<n; (i:1..p)p(i)?put(vr) -> [
            buffer[tail] = vr;
            tail = (tail+1)%n;
            size++;
        ]
        []
        size>0; (j:1..c)c(j)?get -> [
            c(j)!get(buffer[head]);
            head = (head + 1) % n;
            size--;
        ]
    ]
]