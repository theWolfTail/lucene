package lucene.chapter2.fieldType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.swing.text.AbstractDocument.Content;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.SortedNumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class CreateIndex {
	public static void main(String[] args) {
		// 创建三个News对象
		News news1 = new News();
		news1.setId(1);
		news1.setTitle("习近平会见美国总统奥巴马，学习国外经验");
		news1.setContent("国家主席习近平9月3日在杭州西湖国家宾馆会见前来出席二十国集团领导人杭州峰会的美国总统奥巴马......");
		news1.setReply(672);
		News news2 = new News();
		news2.setId(2);
		news2.setTitle("北大迎4380名新生，农村学生700多人近年最多");
		news2.setContent("昨天，北京大学迎来4380名来自全国各地及数十个国家的本科新生。其中农村学生共700余名，为近年最多......");
		news2.setReply(995);
		News news3 = new News();
		news3.setId(3);
		news3.setTitle("特朗普宣誓(Donald Trump)就任美国第45任总统");
		news3.setContent("当地时间1月20日，唐纳德·特朗普在美国国会宣誓就职，正式成为美国第45任总统。");
		news3.setReply(1872);
		
		//创建分词器
		Analyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig icw = new IndexWriterConfig(analyzer);		//设置创建索引所需要的分类器
		icw.setOpenMode(OpenMode.CREATE);
		Directory dir = null;
		IndexWriter inWriter = null;
		// 设置索引目录
		Path indexPath = Paths.get("indexdir");
		// 开始时间
		Date start = new Date();
		try {
			if(!Files.isReadable(indexPath)){
				System.out.println("Document directory '"+indexPath.toAbsolutePath()+"'does not exist or is not readable, please check the path");
				System.exit(1);
			}
			dir = FSDirectory.open(indexPath);
			inWriter = new IndexWriter(dir, icw);		// 用IndexWriter创建索引文档，dir是索引路径，icw是分词器
			//设置新闻ID索引并存储
			FieldType idType = new FieldType();
			idType.setIndexOptions(IndexOptions.DOCS);		// 用setIndexOptions设置域的索引选项，IndexOptions.DOCS是只创建索引
			idType.setStored(true);		//setStored(true)代表存储字段值
			// 设置新闻标题索引文档、词项频率、位移信息和偏移量，存储并词条化
			FieldType titleType = new FieldType();
			titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			titleType.setStored(true);
			titleType.setTokenized(true);  		// 会使用配置的分词器对字段值进行词条化
			
			FieldType contentType = new FieldType();
			contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			contentType.setStored(true);
			contentType.setTokenized(true);
			contentType.setStoreTermVectors(true);			// 保存词向量
			contentType.setStoreTermVectorOffsets(true);	// 保存词项在词向量中的偏移信息
			contentType.setStoreTermVectorPositions(true);	// 保存词项在词向量中的位移信息
			contentType.setStoreTermVectorPayloads(true);	// 保存词项在词向量中的附加字段
			Document doc1 = new Document();
			doc1.add(new Field("id",String.valueOf(news1.getId()),idType));		// Field???
			doc1.add(new Field("title",news1.getTitle(),titleType));
			doc1.add(new Field("content",news1.getContent(),contentType));
			doc1.add(new IntPoint("reply",news1.getReply()));
			doc1.add(new StoredField("reply_display",news1.getReply()));
			Document doc2 = new Document();
			doc2.add(new Field("id",String.valueOf(news2.getId()),idType));		
			doc2.add(new Field("title",news2.getTitle(),titleType));
			doc2.add(new Field("content",news2.getContent(),contentType));
			doc2.add(new IntPoint("reply",news2.getReply()));
			doc2.add(new StoredField("reply_display",news2.getReply()));				// 只保存字段，不进行其他任何操作
			Document doc3 = new Document();
			doc3.add(new Field("id",String.valueOf(news3.getId()),idType));		
			doc3.add(new Field("title",news3.getTitle(),titleType));
			doc3.add(new Field("content",news3.getContent(),contentType));
			doc3.add(new IntPoint("reply",news3.getReply()));
			doc3.add(new StoredField("reply_display",news3.getReply()));
			inWriter.addDocument(doc1);
			inWriter.addDocument(doc1);
			inWriter.addDocument(doc1);
			inWriter.commit();		// 创建索引
			inWriter.close();
			dir.close();
			
	 	} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Date end = new Date();
		System.out.println("索引文档用时："+(end.getTime()-start.getTime())+"milliseconds");
	}
}
