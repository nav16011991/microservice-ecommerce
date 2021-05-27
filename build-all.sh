echo "Starting to build images for all services"


echo "Building Core-event-lib"
cd event-lib

gradle build 

echo "Installing to Local Maven Core-event-lib"
gradle install 

echo "Publishing Core-event-lib"
gradle publishToMavenLocal

echo "Build complete"
cd ..

echo "Login into dockerhub"
docker login -u naveenbhardwaj -p Te@mw0rk

echo "Building Discovery-service"
cd discovery-service
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/discovery-service:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/discovery-service:1.0.0

cd ..

echo "Building application-gateway"
cd application-gateway
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/application-gateway:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/application-gateway:1.0.0

cd ..

echo "Building account-service"
cd account-service
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/account-service:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/account-service:1.0.0

cd ..

echo "Building auth-service"
cd auth-service
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/auth-service:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/auth-service:1.0.0

cd ..

echo "Building inventory-management-service"
cd inventory-management-service
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/inventory-management-service:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/inventory-management-service:1.0.0

cd ..

echo "Building order-service"
cd order-service
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/order-service:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/order-service:1.0.0

cd ..

echo "Building payment-service"
cd payment-service
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/payment-service:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/payment-service:1.0.0

cd ..

echo "Building shipping-service"
cd shipping-service
gradle build

echo "Building docker image"
docker build -t naveenbhardwaj/shipping-service:1.0.0 .

echo "Pushing docker image"
docker push naveenbhardwaj/shipping-service:1.0.0

cd ..