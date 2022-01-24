//skup

s:: content(0..99) integer;
    size:integer; size:= 0;
    *[
        n:integer;
        X?has(n) -> SEARCH; X!(i<size)
        []
        n:integer;
        X?insert(n) -> SEARCH;
            [
                i<size -> skip
                []
                i=size;size<100 -> 
                    content(size) := n; size := size + 1
            ]
    ]

SEARCH:
i:integer; i:=0;
*[i<size;content(i)<>n -> i:=i+1]


//dodatak:

[]
X?scan() -> i:integer; i := 0;
    *[
        i<size -> X!next(content(i)); i:=i+1;
    ]
    X!noneleft()