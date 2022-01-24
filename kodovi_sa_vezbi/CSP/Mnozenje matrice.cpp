// mnozenje matrice
// primer rada sistolicke obrada
[M(i:1..3, 0)::WEST || M (0, j:1..3)::NORTH || M (i:1..3, 4)::EAST || M(4, j:1..3)::SOUTH || M(i:1..3, j:1..3)::CENTER]

NORTH:: *[
    true -> M(1, j)!0
]

EAST:: *[
    x:real;
    M(i, 3)?x -> skip
]

CENTER:: *[ x:real;
    M(i, j-1)?x -> 
    M(i, j+1)!x
    sum:real;
    M(i-1, j)?sum;
    M(i+1, j)!(A(i,j)*x + sum)
]
