# HierarchicalView

HierachicalView is a library which allows to display views tree  of your application.


## Download

Download the latest AAR from Maven Central or grab via Gradle:

```bash
implementation 'com.beingmomin:hierarchicalview:0.1.3'
```

or Maven:

```xml
<dependency>
  <groupId>com.beingmomin</groupId>
  <artifactId>hierarchicalview</artifactId>
  <version>0.1.3</version>
</dependency>
```


## In Code

To integrate this library in your app, you have to implement interface "BinderView" on your activity or fragment like below 

```kotlin
class MainActivity : AppCompatActivity(), BinderView<HierarchyDataModel> {

    override fun onCreateViewHolder(obj: HierarchyDataModel): View {
        val view = layoutInflater.inflate(R.layout.layout_hierarchy_child, null)
        view.tv_person_name.setText(obj.name)
        return view
    }

    override fun getHierarchyData(): HierarchyDataModel {
        return hierarchyDataObject
    }

    override fun getLayoutMaximumWidth(): Float {
        return 60f
    }
    
}
```

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

