# Softwares required

		JDK 8
		Maven
		Tortoise Git client
		Eclipse Kepler
		Oracle VirtualBox
		Minishift

# Steps to check if the Minikube Platform is running
		
		minikube start --vm-driver=virtualbox
	    minikube dashboard
	   
# Steps to Create a new module
1. Create a new module from the Archetype : Give the -DgroupId and -DartifactId parameter 
	
		mvn archetype:generate  -DarchetypeGroupId=com.att.oce -DarchetypeArtifactId=oce-minishift-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.att.oce -DartifactId=testocenewproject
		
		DarchetypeGroupId - Refers to the Group Id of the Archetype project . This will always remain constant.
		DarchetypeArtifactId - Refers to the Artifact Id of the Archetype project . This will always remain constant.
		DarchetypeVersion - Refers to the Version of the Archetype project . This will always remain constant.
		DgroupId - Refers to the Group Id of the new project to be created. This can follow a predefined namespace like	   