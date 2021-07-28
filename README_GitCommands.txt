git checkout master --> to checkout master branch
git checkout -b test2 master  --> this will create test2 branch from master branch
git status --> to check the status of the untracked file or un staged files
git add * -> for adding all files once or git add that file
git commit -m "some commit message" --> committing the changes to repo
git push origin master --> for pushing the changes

git branch --> list the branches locally 
git branch -r -->  list branches remotely

git remote add origin --> for adding the origin
git remote -v    --> this will give you origin 
git remote add upstream --> add our original repo URLin here

stash:
git stash --> stashing the local commits
git stash apply --> release the stash changes
git stash list


git config --> to set the credentials to interact with git
git init --> for initialiazing the repo locally

Advanced git commands:
git merge branchA --> merging changes from branchA into branchB locally, be  on branchB, 
git merge --squash branchName --> be on the master branch, merging multiple commits from branch 'branchName' into 'master' branch, squash multiple commits into sinlgle commit 

git reset –soft  HEAD << number>>  and then git commit 


git diff origin/master..HEAD --> Viewing un pushed Git commits (locally add&commit,but not yet committed to the remote fork
git diff-tree -r {hash}  find a list of files that has been changed in a particular commit

git branch -d localBranchName --> // delete branch locally

git push origin --delete remoteBranchName -->// delete branch remotely

git branch –merged --> lists the branches that have been merged into the current branch.
git branch –no-merged --> lists the branches that have not been merged.

git log --format="%H" -n 1  this will get the commit id n represents last number of commits 
