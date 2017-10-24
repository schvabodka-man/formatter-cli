Hi!

# What works?
Everything, except for "reduction" of words. They're new sentences for the app

The app is done with functional stuff and the core logic itself is done in pure functions. 
 App works by simple regexps. No binary jar provided but if you wan't to just run maven assembly task. It will build you fully working jar.
 
# CLI interface
App takes args from -s key and simply prints result to stdout.

# Stuff from me
 * "Smart" upcasing of the first char. The app is upcasing not just first char, but first letter in sentence.
 * Indenting of quote marks(«» and stuff like that)