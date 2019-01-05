# AndroidSkeletonHelper
This is a java project build with maven to generate the skeleton of 
a new Android app with [AndroidX](https://developer.android.com/jetpack/androidx/) in [Kotlin](https://kotlinlang.org/)
with [AndroidArchitectureComponent](https://developer.android.com/topic/libraries/architecture/).

## GeneratedStructure:
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


## How to Use :
1. Download the [AndroidSkeletonHelper.zip](https://github.com/aashish-aadarsh/AndroidSkeletonHelper/raw/master/AndroidSkeletonHelper.zip) and extract to some directory.
2. Go to extracted directory, you will find `AndroidSkeletonHelper.jar` and `properties` 
directory.
3. Go to `properties` directory :   
       i. Modify `generator.properties` with desired property value.  
       ii. Modify `version.properties` with desired value.  
       iii. Create the `json` file for which entities has to be generated.

4. Open command prompt and change directory where jar file is present and execute bellow command.     
         `java -jar AndroidSkeletonHelper`  
         
    Make sure jar and properties directory are present in same directory.
 5. Wait till application finishes its execution.
 6. Import the skeleton in Android studio.
 
      Enjoy coding. :)
        
        
## Issues
Feel free to report any issues, change request [Here](https://github.com/aashish-aadarsh/AndroidSkeletonHelper/issues)

#

##### Credits:
[Aashish Aadarsh](https://github.com/aashish-aadarsh) 
