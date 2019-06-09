# R.E.S.P.E.C.T

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![](https://jitpack.io/v/GC-RESPECT/RESPECT.svg)](https://jitpack.io/#GC-RESPECT/RESPECT)

Awesome experience using VR video walking!

This library based on [GearVR Framework](http://www.gearvrf.org).

So, this library has dependency on GearVR Framework.

Please Check up [Project Setup Guide](https://github.com/GC-RESPECT/R.E.S.P.E.C.T/wiki/Sample-code#project-setup)

## Installation

build.gradle(Project)

```yaml
allprojects {
	repositories {
		maven {
			url "https://jitpack.io"
		}
	}
}
```

build.gradle(App)

```yaml
dependencies {
	implementation 'com.github.GC-RESPECT:RESPECT:1.0.0'
}
```

## Usage

RespectPlayer into GVRActivity

```java
RespectPlayer mPlayer;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	mPlayer = new RespectPlayer(this, "YOUR_VIDEO_PATH");
	setMain(mPlayer);
}

@Override
protected void onResume() {
	super.onResume();
	
	mPlayer.onResume();
}

@Override
protected void onPause() {
	super.onPause();
	
	mPlayer.onPause();
}
```

## Methods

| name                                                   | description                                         |
| ------------------------------------------------------ | --------------------------------------------------- |
| ```RespectPlayer(GVRActivity activity, String path)``` | Constructor for player.                             |
| ```onResume()```                                       | This method must call on activity's onResume state. |
| ```onPause()```                                        | This method must call on activity's onPause state.  |
| ```setFilePath(String path)```                         | Set up new file path.                               |
| ```setPlayerSpeed(double speed)```                     | Set player speed (Default 1.0)                      |

## License

```
Copyright 2019 GC-RESPECT

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

