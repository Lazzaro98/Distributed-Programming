//prosledjivanje karaktera

[X:: *[c: character; west?c -> east!c]
|| 
west: *[c: character; X!c]
||
east: *[c: character; X?c]
]


