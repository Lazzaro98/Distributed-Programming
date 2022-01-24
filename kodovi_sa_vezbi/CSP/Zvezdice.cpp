[
    X:: 
        *[
        c: character;
        west?c -> [
            c!='*' -> east!c
            []
            c=='*' -> [
                west?c -> [
                    c!='*' -> [east!'*';east!c]
                    []
                    c=='*' -> east!'^'
                ]
            ] 
        ]
        ]        
    || 
    west: *[c: character; X!c]
    ||
    east: *[c: character; X?c]
]
