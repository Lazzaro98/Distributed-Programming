// a

FORMAT :: *[
    blanks : boolean; blanks := false;
    UCASE : boolean; UCASE := false;
    *[
        c:character;
        SRC?c -> [
            c == " " -> blanks = true;
            []
            c == "." -> UCASE := true; [blanks->DST!" "; DST!"."] blanks := false;
            []
            c !=" ", c != "." -> [
                [blanks -> DST!" "]
                blanks := false;
                [
                    UCASE -> DST!upper(c); UCASE := false
                    []
                    not(UCASE) -> DST!lower(c)
                ]
            ]
        ]
    ]
]


// vezba modif -> Svaki karakter koji se ponavlja vise puta zamenjuje se @<charatcter>(broj ponavljanja)

FORMAT :: [
    last_char:character; last_char := none;
    counter:int; counter := 0;
    *[
        c:character;
        SRC?c -> [
            c == last_char -> counter := counter + 1;
            []
            c != last_char -> [
                counter > 0 -> DST!"@<" + c + ">" + counter; counter := counter + 1; last_char := c;
                []
                counter == 0 -> DST!c;
            ]
        ]

    ]
]

// b (bez CR). 

FORMAT :: [
    last_char:character;
    counter:integer; counter := 0;
    *[
        SRC?c -> [
            counter == N -> DST!"@<" + c + ">(" + counter + ")"; counter := 0;
            []
            c==last_char -> counter := counter + 1;
            []
            c!=last_char -> counter:=0; last_char:=c; DST!c;
        ]
    ]
]