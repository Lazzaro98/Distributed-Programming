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

mbx rw;
void coordinator() {
    boolean status;
    msg m, n;
    int cntR = 0, cntW = 0;
    while(1){
        if(buffer.size() > 0 &&
            (buffer.peak().operation == "start read" &&c cntW == 0) ||
            (buffer.peak().operation == "start write" &&c cntW + cntR == 0)){
                m = buffer.remove();
        }
        else {
            mbx_get(m, rw, INF, status);
        }
        switch(m.operation){
            case "start read":
                if(cntW > 0 || buffer.size>0) {
                    buffer.add(m);
                }
                else {
                    cntR++;
                    mbx_put(m, r[m.id]);
                }
            break;
            case "start write":
                if(cntW + cntR > 0 || buffer.size>0) {
                    buffer.add(m);
                }
                else {
                    cntW++;
                    mbx_put(m, w[m.id]);
                }
            break;
            case "end read":
                cntR--;
            break;
            case "end write":
                cntW--;
            break;
       }
    }
}