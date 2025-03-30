int x = 5;
int *p;

int foo() {
  int x = 10;
  return x;
}

int main() {
  int y = foo() + 1;
  int a[10];

  a[0] = 5;
  a[1] = 10;

  *p = 10;

  if(y > 5) {
    x = 10;
  } else {
    x = 20;
  }

  while(1) {
    x = x + 1;
    if(x > 100) {
      break;
    }
  }

  for(int i = 0; i < 10; i++) {
    x = x + 1;
  }

  
  
  return x;
}
