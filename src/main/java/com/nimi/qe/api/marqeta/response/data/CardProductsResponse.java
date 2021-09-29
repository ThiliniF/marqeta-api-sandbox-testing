package com.nimi.qe.api.marqeta.response.data;

import java.util.List;

public class CardProductsResponse {

    public int count;
    public int start_index;
    public int end_index;
    public boolean is_more;
    public List<Datum> data;
}
