# Description
Extra Mod Installer is a mod installer that is compatible with Minecraft Forge based modpacks. It allows easy download and installation of mods and configs that are placed into their respective directories.

## Mod List
The mod list is a simple JSON file that contains display names, files names, and relative install locations for the configs and mods.

```
{
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
}
```

URLs for the mod list, mod root directory, and config root directory should be placed in the configuration file.  (Not yet implemented, but should be in version 0.2)
```
{
  "modlistURL": "",
  "modRootURL": "",
  "configRootURL": ""
}
```

# Dependencies
google-gson <https://code.google.com/p/google-gson/>