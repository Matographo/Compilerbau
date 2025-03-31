# Compilerbau

## Installation & Start

### Prerequisites

* UNIX-System
* Node `v23.0.0` or greater ([nvm](https://github.com/nvm-sh/nvm) is recommended)
* Maven `v3.9.4` or greater
* gcc (C Compiler)
* g++ (C++ Compiler)
* Make

### Setup

1. Navigate to the `tree-sitter-cma` directory

```zsh
cd tree-sitter-cma
```

2. Install the Node dependencies

```zsh
npm install
```

3. Navigate to the `cma` directory

```zsh
cd ../cma
```

4. Compile the Java project with maven

```zsh
mvn clean install
```

5. Move back to the main directory

```zsh
cd ..
```

6. Run the `compile.sh` script with the files to be compiled as arguments

```zsh
./compile.sh test1.c test2.c
```

        The `debug` option is available
```zsh
./compile.sh test1.c test2.c debug
```