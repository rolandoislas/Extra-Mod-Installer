# Description
Extra Mod Installer is a mod installer that is compatible with Minecraft Forge based modpacks. It allows easy download and installation of mods and configs that are placed into their respective directories.

## Mod List
The mod list is a simple JSON file that contains display names, files names, and relative install locations for the configs and mods.

```
{
  "install": {
    "mod": [
      {
        "displayName": "",
        "fileName": ""
      }
    ],
    "config": [
      {
        "displayName": "",
        "fileName": "",
        "install": ""
      }
    ]
  },
  "remove": {
    "mod": 
    {
      "displayName": "",
      "fileName": "",
      "install": ""
    },
    "config": 
    {
      "displayName": "",
      "fileName": "",
      "install": ""
    }
  }
}
```

# Config
If using a precompiled binary, the configuration file (config.json) can be found within the jar in a folder named config.

The following is an example of a populated config.json.
```
{
  "listURL": "http://example.com/list.json",
  "modRootURL": "http://example.com/mods/",
  "configRootURL": "http://example.com/config/",
  "defaultInstallLocation": {
    "win": {
        "useHomeDirectory": true,
  	  "directory": "/install/path/from/users/home/directory/"
  	},
  	"osx": {
  	  "useHomeDirectory": false,
  	  "directory": "/full/install/path/"
  	},
  	"nix": {
  	  "useHomeDirectory": true,
      "directory": "/.someLauncher/install/path/"
  	}
  },
  "launcher" {
    "isAtLauncher": false
  }
}
```
With this config, the default install directory for Windows resolves to "C:\Users\<username>\install\path\from\users\home\directory\".


# Dependencies
google-gson <https://code.google.com/p/google-gson/>