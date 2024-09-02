package DAT250.Project.Rest;

import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/vote-options")
public class VoteOptionController {

    private final DomainManager domainManager;

    @Autowired
    public VoteOptionController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<HashMap<UUID,VoteOption>> createVoteOption(@RequestBody VoteOption voteOption) {
        HashMap<UUID,VoteOption> voteOptionHashMap = domainManager.addVoteOption(voteOption);
        return ResponseEntity.ok(voteOptionHashMap);
    }
    @GetMapping
    public ResponseEntity<Collection<VoteOption>> getAllVoteOptions() {
        return ResponseEntity.ok(domainManager.getAllVoteOptions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VoteOption> getVoteOption(@PathVariable UUID id) {
        VoteOption voteOption = domainManager.getVoteOption(id);
        return voteOption != null ? ResponseEntity.ok(voteOption) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoteOption> updateVoteOption(@PathVariable UUID id, @RequestBody VoteOption voteOption) {
        VoteOption updatedVoteOption = domainManager.updateVoteOption(id, voteOption);
        return ResponseEntity.ok(updatedVoteOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable UUID id) {
        VoteOption deletedVoteOption = domainManager.deleteVoteOption(id);
        return deletedVoteOption != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

