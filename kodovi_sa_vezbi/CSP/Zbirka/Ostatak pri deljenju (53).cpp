// a

DIV :: *[
    x, y: integer;
    X?(x, y) -> quot, rem: integer; quot := 0; rem := x;
    *[
        rem>=y -> rem := rem -y; quot := quot + 1;
    ]
    X!(quot, rem)
]

[division::DIV || X :: USER]



// b

DIV :: *[
    x, y:integer;
    (j:1..N)X(j)?(x,y) -> quot, rem: integer; quot:=0; rem:=x; 
    *[
        rem>=y -> rem := rem - y; quot := quot + 1;
    ]
    X(j)!(quot, rem)
]

[division :: DIV || X(i:1..N) :: USER]