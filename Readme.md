<img src="https://raw.githubusercontent.com/schvabodka-man/formatter-cli/master/img/logo.png" width="370" height="400">


<div style="display: inline-block;">
<img src="https://raw.githubusercontent.com/schvabodka-man/Custom-Badges/master/Languages/Java/png/Java%20xxxhdpi.png" width="300" height="46">
<img src="https://raw.githubusercontent.com/schvabodka-man/Custom-Badges/master/BuildFor/CLI/CLI.png" width="300" height="46">
</div>

# What is this?
The simple CLI util to help you make proper formatting of any human-language string. For example, you input "the quick brown fox ,jumps Over The , lazy dog ...   " and get "The quick brown fox, jumps over the, lazy dog...". Magic!

# Provided functionality
## Primary stuff
* "Smart" regex based word casing.
* Formatting of quotes
* Formatting of commas, semilocons, etc.
* Proper spacing for everything
## Secondary stuff
* Fancy diff with colorization
* Possibility to set your own ansi codes/chars for diff
* Mode for interactive input instead of cli
## The code
Pretty neat stuff, the core logics is in fact purely functional.

# CLI params
* -an,--ansi-new <arg>   Ansi char for new stuff in diff
* -ao,--ansi-old <arg>   Ansi char for old stuff in diff
* -c,--color             Colorize output
* -cn,--char-new <arg>   Char to show for new stuff in diff
* -co,--char-old <arg>   Char to show for old stuff in diff
* -d,--diff              Show diff
* -h,--help              Help
* -i,--interactive       Run app interactively
* -s,--string <arg>      Input string, this one is **REQUIRED**. 

# What's used?
Apache Commons libraries(mostly for cli interface), that's it. There is also Junit tests. No bloat.

# Why Java? This stuff should be written on C/Python/Go
The performance overhead for that small application isn't really that bad at all.

# Probable improvements
I really don't know what you can improve. Maybe some dictionary based upcasing and support for different languages(the current version of app won't obliviously work with chineese/japanese/hebrew/arabic/hindi rules of punctuation, only latin/cyrillic-alphabet european languages)? Anyway i would be glad to see some PR.
