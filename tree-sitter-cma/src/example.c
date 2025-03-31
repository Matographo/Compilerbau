int x = 5;
int * p;

int foo() {
  int x = 10;
  return x;
}

int barr(int* a) {
  return *a;
}   

void bar(int b, int c) {
  x = 10;
}

int main() {
  
  int y = foo() + 1;
  int a[10];
  int z = 10;

  a[0] = 5;
  a[1] = 10;

  if(y > 5) {
    x = 10;
  } else if (y < 5) {
    x = 20;
  }

  * p = 10;
  bar(10, x);
  while(1) {
    x = x + 1;
    if(x > 100) {
      break;
    }
  }


  return x;
}
