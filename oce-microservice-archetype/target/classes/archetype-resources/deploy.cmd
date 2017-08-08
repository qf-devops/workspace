docker-machine env --shell cmd default
@FOR /f "tokens=*" %i IN ('docker-machine env --shell cmd default') DO @%i

mvn clean install

kubectl -f create deployment.yaml

kubectl -f create services.yaml
