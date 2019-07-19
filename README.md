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

To integrate this library in your app, you have to implement interface ```BinderView<T>``` on your activity or fragment like below. 
  
**Note: T is HierarchyDataModel in which one parameter must be ```List<T>```.**  

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
     
     // maximum width of the layout will be in dp format 
    override fun getLayoutMaximumWidth(): Float {
        // It means the maximum width of childview will be 60dp
        return 60f
    }
    
}
```

BinderView interface overrides the three function as mentioned above.

First function **onCreateViewHolder** will return view of child which will be shown in heirarchy. In this funcion developer will inflate childView and bind data in it.

Second function **getHierarchyData** will return HierarchyDataObject in which whole hierarchy is stored. 

Third function to **getLayoutMaximumWidth** will return the maximum width of childview in dp.

## Widget in XML

```xml
 <com.beingmomin.hierarchicalview.HierarchicalView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:hasClickableChildren="true"
            app:horizontalPanEnabled="true"
            app:maxZoom="3.0"
            app:maxZoomType="zoom"
            app:minZoom="0.7"
            app:minZoomType="zoom"
            app:overPinchable="true"
            app:overScrollHorizontal="true"
            app:overScrollVertical="true"
            app:verticalPanEnabled="true"
            app:zoomEnabled="true"
            />
```
### Special Thanks

[ZoomLayout](https://github.com/natario1/ZoomLayout)


### License
```
Copyright 2019 BeingMomin Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

