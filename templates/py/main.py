import subprocess

def run_java(java_file):
    try:
        #compile the java file
        subprocess.run(["javac", java_file], check=True)
        class_name = java_file.replace(".java","")

        #Run the compiled class
        result = subprocess.run(["java", class_name], capture_output=True, text=True, check=True)

        print("Java Output: ",result.stdout)
    
    except subprocess.CalledProcessError as e:
        print("Error:", e.stderr)


#call with java file name
run_java("helloworld.java")
