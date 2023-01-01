# variables
DOCKER_IMAGE_NAME = price-runner
DOCKER_IMAGE_TAG = latest
DOCKER_USERNAME = bl4ko

# build the maven project
build:
	mvn clean package

# clean up the maven project
clean:
	mvn clean
	find . -name "*.jar" -exec rm -f {} \;

# build the Docker image
docker-build:
	docker build -t $(DOCKER_USERNAME)/$(DOCKER_IMAGE_NAME):$(DOCKER_IMAGE_TAG) .

# push the Docker image to Docker Hub
docker-push:
	# Login to Docker Hub
	cat .env | grep DOCKER_PASSWORD | cut -d '=' -f 2 | docker login -u $(DOCKER_USERNAME) --password-stdin
	docker push $(DOCKER_USERNAME)/$(DOCKER_IMAGE_NAME):$(DOCKER_IMAGE_TAG)

# build the Docker image and push it to Docker Hub
publish: docker-build docker-push
