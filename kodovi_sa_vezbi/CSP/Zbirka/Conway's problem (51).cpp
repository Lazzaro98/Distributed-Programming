// a
COPY :: *[
    c:char;
    west?c -> east!c
]

// b

SQUASH :: *[
    c:char;
    west?c -> [
        c == '*' -> west?c ->[
            c == '*' -> east!'^'
            []
            c != '*' -> east! '*'; east!c;
        ]
        []
        c != '*' -> west!c
    ]
]

// v

DISSASEMBLE:: *[
    *[
        cardimage: (1..80) character;   
        cardfile?cardimage -> 
            i:integer; i := 1;
            *[
                i<=80 -> X!cardimage(i); i := i + 1;
            ]
            X!' '
    ]
]

// g

ASSEMBLE :: [
    lineimage : (1..125)char;
    i:integer; i := 1;
    *[
        c:char;
        i<125; X?c -> lineimage(i) = c; i := i + 1;
        []
        i==125 -> lineprinter!lineimage; i := 1;
    ];
    [
        i==1 -> skip
        []
        i>1 -> *[
            i<=125 -> lineimage(i) = ' '; i := i + 1;
        ]
        lineprinter!lineimage
    ]
]

// d

[west::DISSASEMBLE || X::COPY || east::ASSEMBLE]

// dj

[west::DISSASEMBLE || X::SQUASH || east::ASSEMBLE]