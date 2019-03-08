# PixParticles

A particle renderer, released in the hope it may be useful. Written in Scala with no dependencies, beyond the µTest framework. Very... manual.

## Features
Included and working:
* 2D single pixel particles
* 2D line particles
* some really terrible lightning
* a bunch of particle generators full of hardcoded numbers - play away!
* a dumb way of generating looping spritesheets
* a stupid script that uses ImageMagick to generate gifs, look in the util folder. This is super manual, I really just wrote it down because I can never remember how to use it.

Not working
* I started a 3d particle renderer and didn't get very far. There are various fragments of this in the code
* Only two of the LPC palette entries are implemented because only the red and blue ones were fun

## Building and running
PixParticles uses [mill](http://www.lihaoyi.com/mill/) to build. You will first need to install that. Then, in the root run

    mill pixparticles.compile

to compile, and 

    mill pixparticles.run

to run. Note that the operation and output is hardcoded in the main PixParticles.scala file, so you will need to change that.

## How it works
You create an instance of a ParticleGenerator and push it into an instance of a Renderer2D, call render on that, and then use standard java IO to push the BufferedImage into a file. The generator works by creating a bunch of BaseParticle2Ds, which evolve and render themselves.

In PixParticles.scala there are a bunch of commented out lines... they will not work, they are lies, and are there because I couldn't be bothered to refactor my old workings when I settled on the current architecture. 

You could, if you wanted, write your own particles, generator, or even renderer.
