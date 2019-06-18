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
}
StringBuilder sb;
for (int i = 1 ; i < strs.length - 1; i++) {
	sb.append(strs[i]);
	sb.append(" ");
}
context.write(new Text(strs[0]), new Text(sb.toString()));
}
}
