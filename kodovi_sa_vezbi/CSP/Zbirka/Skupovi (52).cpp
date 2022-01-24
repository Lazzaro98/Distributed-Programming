[U :: USER || S :: SET]

SET :: [
    N:integer; N := N; content:(0..N-1) integer;
    size:integer; size := 0;
    *[
        x:integer; U?has(x) -> HAS
        [] 
        x:integer; U?insert(x) -> INSERT
        []
        x:integer; U?delete(x) -> DELETE
        []
        U?size() -> U!size(size)
        []
        U?scan() -> SCAN
        []
        U?min() -> MIN
        []
        U?max() -> MAX
        []
        U?clear() -> size:=0
    ]

    SEARCH :: 1:integer; i := 0; *[i<size; content(i) != x -> i := i + 1]

    HAS :: SEARCH; U!has(i<size)

    INSERT ::
    SEARCH;
    [
        i<size -> U!insert(false); // x is already a member of a set
        []
        i==size -> content(size) = x; size := size + 1; U!insert(true);
    ]

    DELETE ::
    SEARCH;
    [
        i<size -> content(i) := None; size := size - 1; U!delete(true)
        []
        i==size -> U!delete(false)
    ]

    SCAN::
    i:integer; i:=0;
    *[
        i<size -> U!content(i); i := i + 1;
    ]
    i==size -> U!scan(nomore)

    MIN :: 
    m:integer; m := content(0); 
    i:integer; i := 1;
    *[
        i<size; content(i) < m -> m := content(i); 
        i := i + 1;
    ]
    U!min(m);

    MAX::
    m:integer; m := content(0);
    i:integer; i := 1;
    *[
        i<size;content(i)>m -> m:=content(i);
        i := i + 1;
    ]
    U!max(m);
]


USER:: 
    //example of has() usage
    x:integer; x := ...; result:boolean;
    S!has(x); S?has(result);
    [
        result -> // x is member of set
        []
        !result -> //x is not the member of a set
    ]

    // example of insert(c) usage
    c:integer; result:boolean;
    S!size(); S?size(c);
    [
        c<N -> x:integer; x := ...; S!insert(x); S?insert(x);
        [
            result -> //uspesno ubacivanje elementa
            []
            !result -> //element nije uspesno ubacen
        ]
        []
        c>=N -> //set is full
    ]

    //example of delete(x) usage
    c:integer; result:boolean;
    S!size(); S?size(c);
    [
        c > 0 -> x:integer; x := ...; S!delete(x); S?delete(result);
        [
            result -> //uspesno odradjen delete
            []
            !result -> // neuspesan delete
        ]
        c == 0 -> // set is empty
    ]


    // example if size() usage
    c:integer;
    S!size();S?size(c);

    // example of scan() usage
    S!scan();
    more:boolean; more := true;
    *[
        more; m:integer; S?scan(m) -> // do smth with the element that you've just read
        []
        more; S?scan(nomore) -> more := false;
    ]


    // example of max() usage
    m:integer;
    c:integer;
    S!size(); S?size(c);
    [
        c>0 -> S!max(); S?max(m); // do smth with the max value m you just received
        []
        c == 0 -> //set is empty
    ]


    // example of min() usage
    m:integer;
    c:integer;
    S!size(); S?size(c);
    [
        c>0 -> S!min(); S?min(m); // do smth with the min value u just received
        []
        c == 0 -> //set is empty
    ]

    // example of clear() usage
    S!clear();

]
