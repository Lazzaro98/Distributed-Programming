[(i:1..R)R(i)::read || (j:1..W)W(j):write || coord::coord ]

read:: [
    //start read
    coord!startRead();
    coord?okToRead(); 
    ...
    //end read
    coord?endRead();
]

write:: [
    //start write
    coord!startWrite();
    coord?okToWrite(); 
    ...
    //end write
    coord!endWrite();
]

coord:: [
    int cntR = 0;
    int cntW = 0;
    * [
        cntW==0, (i:1..R) R(i)? startRead() -> [
            [
                cntW>0, (i:1..W)W(i)?endWrite() -> [
                    cntW--;
                ]
            ]
            R(i).okToRead();
            cntR++;
        ]
        []      
        cntR==0, cntW==0, (i:1..W) W(i)? startWrite() -> [
            *[
                cntR>0, (i:1..R)R(i)?endRead() -> [
                    cntR--;
                ]
                cntW>0, (i:1..W)W(i)?endWrite() ->[
                    cntW--;
                ]
            ]
            W(i)!okToWrite();
            cntW++;
        ]
        []
        (i:1..R) R(i)? endRead() -> [
            cntR--;
        ]
        []
        (i:1..W) W(i)? endWrite() -> [
            cntW--;
        ]
    ]
]