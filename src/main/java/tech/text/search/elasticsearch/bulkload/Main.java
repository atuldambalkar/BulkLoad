package tech.text.search.elasticsearch.bulkload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tech.text.search.elasticsearch.model.Product;

import java.io.*;

/**
 * Hello world!
 */
public class Main {

    private static Index index = new Index();

    static {
        IndexData indexData = new IndexData();
        indexData._index = "bestbuy";
        indexData._type = "product";

        index.setIndex(indexData);
    }

    public static void main(String[] args) throws Exception {
        Reader reader = null;
        Writer writer = null;
        try {
            reader = new InputStreamReader(new FileInputStream(args[0]));
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            Product[] products = gson.fromJson(reader,
                    Product[].class);
            writer = new FileWriter(args[1]);
            for (Product product: products) {
                gson.toJson(index, writer);
                writer.write("\n");
                gson.toJson(product, writer);
                writer.write("\n");
            }
            System.out.println("Hello World!");
        } finally {
            reader.close();
            writer.close();
        }

    }

    private static class Index {
        private IndexData index;

        public IndexData getIndex() {
            return index;
        }

        public void setIndex(IndexData index) {
            this.index = index;
        }
    }

    private static class IndexData {
        private String _index;
        private String _type;

        public String get_index() {
            return _index;
        }

        public void set_index(String _index) {
            this._index = _index;
        }

        public String get_type() {
            return _type;
        }

        public void set_type(String _type) {
            this._type = _type;
        }
    }
}

