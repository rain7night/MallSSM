package search;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class SolrTest {

    @Test
    public void testAdd() throws SolrServerException, IOException {
        //创建一个连接，和solr集群的,参数就是zookeeper的地址列表，
        String solrUrl = "http://192.168.186.128:8983/solr";
        SolrServer solrServer = new HttpSolrServer(solrUrl);

        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();

        //向文档中添加域
        document.addField("id", "test001");
        document.addField("item_title", "测试测试");

        //把文档添加到索引库
        solrServer.add(document);
        solrServer.commit();

    }


}
