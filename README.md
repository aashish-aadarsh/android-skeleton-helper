# AndroidSkeletonHelper
This is a java project build with maven to generate the skeleton of 
a new Android app.

###GeneratedStructure:
1. Version of dependencies, download repository and other build.gradle properties are resolved from versions.gradle
2. Static resources such as layout directory, menu directory, drawables and others.
3. Android Manifest File with entry of activities, permission tag. 
The generated manifest file does not specify default launcher activity.
4. The packages are generated in five category:
        
        1. vo - Value Object for constants and other
        2. view - UI related java classes in their module having activity, fragment, 
                 viewmodel, adapter and adapterVO depending on configuration specified.
        3. utility - For generic utility or helper class
        4. core - Core application classes.
        5. datasource -  Contains database( and converter, dao, entity, helper), 
                           network(and retrofit helper)
                           and repository ( and its impl)         








##### Credits:
[ApacheVelocity](http://velocity.apache.org/)  
[Kotlin](https://kotlinlang.org/)   
[AndroidArchitectureComponent](https://developer.android.com/topic/libraries/architecture/)