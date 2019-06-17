import java.io.*;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class PageRankMapper
    extends Mapper<LongWritable, Text, Text, Text> {
@Override
public void map(LongWritable key, Text value, Context context)
throws IOException, InterruptedException {
String line = value.toString();
String[] strs = line.split(" ");
int length = strs.length;
int outlinks = length - 2;
double PR = Double.valueOf(strs[length - 1]); 
for (int i = 0; i < outlinks; i++) {
	context.write(new Text(strs[1 + i]), new Text(strs[0] + " " + PR/outlinks));
	System.out.println(strs[1 + i] + " " + strs[0] + " " + PR/outlinks);
}
context.write(new Text(Character.toString(line.charAt(0))), new Text(line.substring(2, 3+(outlinks-1)*2)));
System.out.println(Character.toString(line.charAt(0)) + " " + line.substring(2, 3+(outlinks-1)*2));
}
}
