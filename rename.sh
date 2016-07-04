#rename files of eyebrow image in offical lianmeng
for file in `ls *.png`
do
 mv "$file" `echo "$file" | sed 's/\(.*\_.*\_\)\(.*\)/\eyebrow_\2/g'`
done