// faktorijel

[fac(i:1..LIMIT):f(i)::f || USER::user]

f:: *[
    n:integer;
    fac(i-1)?n -> [
        n=0 -> fac(i-1)!1
        []
        n>0 ->  fac(i+1)!n-1;
                r:integer; fac(i+1)?r;
                fac(i-1)!(n*r)
    ] 
]

user :: [
    fac(0)::
]