mbx[R] r;

void reader(int id) {
    boolean status;
    msg m;
    while(1){
        //start_read
        m.id = id;
        m.operation = "start read";
        mbx_put(m, start);
        mbx_get(m, r[id], INF, status);
        read();
        //end_read
        m.id;
        m.operation = "end read";
        mbx_put(m, end);
    }
}

mbx[W] w;
void writer(){
    boolean status;
    msg m;
    while(1){
        //start_Write
        m.id = id;
        m.operation = "start write";
        mbx_put(m, start);
        mbx_get(m, w[id], INF, status);
        read();
        //end_read
        m.id;
        m.operation = "end write";
        mbx_put(m, end);
    }
}

mbx start, end;
void coordinator() {
    boolean status;
    msg m, n;
    int cntR = 0, cntW = 0;
    while(1){
        mbx_get(m, start, INF, status);
        if(m.operation == "start read"){
            if(cntW == 0){
                mbx_put(m, r[m.id]);
                cntR++;
            }
            else {
                mbx_get(n, end, INF, status);
                cntW--;
                mbx_put(m, r[m.id]);
                cntR++;
            }
        }else {
            if(cntR+cntW==0){
                mbx_put(m, w[m.id]);
                cntW++;
            }
            else if(cntW>0) {
                mbx_get(n, end, INF, status);
                mbx_put(m, w[m.id]);
            }
            else {
                while(cntR>0){
                    mbx_get(n, end, INF, status);
                    cntR--;
                }
                mbx_put(m, w[m.id]);
                cntW++;
            }
        }
    }
}