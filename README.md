

# Ender Pearl Ride plugin
A Minecraft plugin that makes the players ride the Ender Pearls they throw.
## Description
Players that through Ender Pearls start riding them. 
Players are immune from suffocation, falling and flying into wall while they are riding an enderpearl.
Players can still be hit. A player that is hit, either by melee or projectile, will be ejected by the Ender Pearl. The Ender Pearl will continue flying.
## Installation

 1. Use either [spigot](https://www.spigotmc.org/wiki/spigot-installation/) or [paper](https://papermc.io/downloads/paper)
 2. Download the plugin from the [releases](https://github.com/S-Dhir/Pearl-Ride-Plugin/releases) page.
 3. Move it to the plugins folder. 
 4. That's it!

Note that the plugin is in pre-release stage due to lack of configurability.

 
## Future planned features
### Commands
`/toggleEnable` - Enables/Disables plugin

`/deletePearls` - When a player throws an Ender Pearl while riding an Ender Pearl, what should happen to the Pearl that the player is riding?
### Crafting
Instead of the plugin completely modifying the way Ender Pearls work, I plan to add a completely new item that behaves in this way.

![Plan For future Crafting Recipe](https://github.com/S-Dhir/Pearl-Ride-Plugin/blob/main/FuturePlan.jpg?raw=true)
### Demo Video
Mod Showcasing
### Fix NoGravity
EnderPearls with NoGravity attribute will very quickly slow down. However, I plan to make their velocity constant.
## Compiling yourself
The project was compiled manually using [Artifacts](https://www.jetbrains.com/help/idea/working-with-artifacts.html#examples) in [Intellij IDEA](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwj4x9jh8pSKAxUpTGwGHdDHERcQFnoECA0QAQ&url=https://www.jetbrains.com/idea/&usg=AOvVaw1dUbYp3QZeAgvbt0Z6D1Zw&opi=89978449). The project does not use maven/gradle. 
Apart from SpigotAPI, CraftBukkit and Bukkit and their dependencies, there are no other dependencies. 

## Spigot page
The plugin is not ready yet to be published on Spigot. But you can download an early version from the releases page on the Github.
## Authors
All files here were written from scratch, mostly for practice by the following developers (first involved to last involved):

 - Shiven Dhir
## License
You can do anything as long as you credit the author(s) of this work. Private and Commercial use is permitted.

## Warranty
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
